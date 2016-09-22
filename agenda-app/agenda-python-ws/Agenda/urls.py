from django.conf.urls import patterns, include, url
from django.conf.urls.static import static
from AgendaPython import settings
from Agenda import views

urlpatterns = patterns('Agenda.views',
	url(r'^$', views.index, name='index'),
	url(r'^cadastro/$', views.Criar.as_view(), name = 'cadastro'),
	url(r'^contatos/$', views.contato_list),	
	url(r'^update/(?P<pk>\d+)/$', views.ContatoUpdate.as_view()),
	url(r'^delete/(?P<pk>\d+)/$', views.ContatoDelete.as_view()),
	url(r'^postimage/$', views.handle_uploads),		
	
	#(r'^media/(.*)$', 'django.views.static.serve', {'document_root': settings.MEDIA_ROOT}),	
	# url(r'^media/(?P<path>.*)$', 'django.views.static.serve', {'document_root' : settings.MEDIA_ROOT}),	
) 