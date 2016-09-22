from django.forms import widgets
from rest_framework import serializers
from Agenda.models import Contato

class ContatoSerializer(serializers.ModelSerializer):
	#def getId( self, attrs, instance=None):
	#	return attrs.get('id', instance.id)

	class Meta:	    
		model = Contato		
		fields = ('id', 'name', 'fone', 'email', 'photo')	
		
		
#class ContatoSerializer(serializers.Serializer):
#	pk = serializers.Field()
#	name = serializers.CharField(required=False,
#									max_length=100,)
#	fone = serializers.CharField(required=False,
#									max_length=50)
#	email = serializers.CharField(required=False,
#									max_length=50)
#	
#	def restore_object(self, attrs, instance=None):
#		
#		if instance:
#			# upgrade instance
#			instance.nome = attrs.get('name', instance.name)
#			instance.fone = attrs.get('fone', instance.fone)
#			instance.email = attrs.get('email', instance.email)
#			return instance
#			
#		return Contato(**attrs)