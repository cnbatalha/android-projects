from django.db import models
from django.forms import forms
from django.conf import settings
from pygments.lexers import get_all_lexers
from pygments.styles import get_all_styles


LEXERS = [item for item in get_all_lexers() if item[1]]
LANGUAGE_CHOICES = sorted([(item[1][0], item[0]) for item in LEXERS])
STYLE_CHOICES = sorted((item, item) for item in get_all_styles())

# Create your models here.
class Contato(models.Model):
	id = models.AutoField(primary_key=True)
	name = models.CharField(max_length=200)
	fone = models.CharField(max_length=15)	
	email = models.EmailField(max_length=60)
	photo = models.ImageField('Foto: ',upload_to='media/photos', default='media/photos/none/no-image.jpg', blank=True, null=True, max_length = 255)
	
#def foo(instance, filename), 
#	return "%s/%s"% (instance.nome,filename)