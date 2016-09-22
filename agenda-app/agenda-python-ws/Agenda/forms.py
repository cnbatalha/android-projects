from django import forms
from Agenda.models import Contato

class ContatoForm(forms.ModelForm):

		class Meta:
				model = Contato