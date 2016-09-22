from django.shortcuts import render_to_response, render

from django.http import HttpResponse, HttpResponseRedirect, StreamingHttpResponse
from django.core import serializers

from django.template import RequestContext, loader

#from django.contrib.auth.forms import UserCreationForm
from django.views.generic import CreateView, ListView, UpdateView, DeleteView

from django.core.urlresolvers import reverse_lazy
from django.views.decorators.csrf import csrf_exempt

from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser

from Agenda.models import Contato
from Agenda.forms import ContatoForm
from Agenda.serializers import ContatoSerializer

# Create your views here.
def index(request):
	lista_contatos = Contato.objects.all()
	template = loader.get_template('agenda/index.html')
	context = RequestContext( request, { 'lista_contatos': lista_contatos,})
	return HttpResponse(template.render(context))

def update(request, id):     
	instance = Contato.objects.get(id=id)  
	form = ContatoForm(request.POST or None, instance=instance)	
	if form.is_valid():           
		form.save()           
		return redirect('index') 
	return direct_to_template(request, 'index', {'form': form})
	
class Criar(CreateView):
	template_name = 'agenda/cadastro.html'
	model = Contato
	success_url = reverse_lazy('index')
	
class ContatoUpdate(UpdateView):
	model = Contato	
	template_name = 'agenda/cadastro.html'
	success_url = reverse_lazy('index')	
	
class ContatoDelete(DeleteView):
	template_name = 'agenda/delete.html'
	model = Contato
	success_url = reverse_lazy('index')	
	
class JSONResponse(StreamingHttpResponse):
	def _init_(self, data, **kwargs):
		content = JSONRenderer().render(data)
		kwargs['mimetype'] = 'application/json'
		super(JSONResponse, self)._init_(content, **kwargs)		

@csrf_exempt

def handle_uploads(request): 
	if request.method == 'POST':        
		uploaded_file = request.FILES['file']		
		#json = request.FILES['data']        
		file_name = 'media/photos/' + uploaded_file.name	         
		# Write content of the file chunk by chunk in a local file (destination)        
		with open( 'media/'+file_name, 'wb+') as destination:            
			for chunk in uploaded_file.chunks():                
				destination.write(chunk)        
		# Create your object		
		last = Contato.objects.latest('id')
		Contato.objects.filter(id=last.id).update(photo= file_name)
		#obj.save()
	response = HttpResponse(last.id)    
	return response	
	
@csrf_exempt
def contato_list(request):

	if request.method == 'GET':
		contatos = Contato.objects.all()
		serializer = ContatoSerializer(contatos, many=True)
		json = JSONRenderer().render(serializer.data)
		#return HttpResponse(serializer.data)
		return HttpResponse(json, mimetype="application/json")
		#return JSONResponse(serializer.data)
		
	elif request.method == 'POST':
		data = JSONParser().parse(request)
		serializer = ContatoSerializer(data=data)
		#ob = serializers.deserialize("json", data)
		#return HttpResponse(ob, mimetype="application/json")
		if serializer.is_valid():
			serializer.save()
#			last = Contato.objects.latest('id')
#			lastid = last.id
			return JSONResponse('OK', status=201)
		return JSONResponse(serializer.errors, status=400)