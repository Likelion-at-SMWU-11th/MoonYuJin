from django import forms
from django.contrib.auth import get_user_model
from django.contrib.auth.forms import UserCreationForm

class UserBasedForm(forms.ModelForm):
    class Meta:
        model = get_user_model()
        fields = '__all__'

class UserCreateForm(UserBasedForm):
    password2 = forms.CharField(widget=forms.PasswordInput)
    class Meta(UserBasedForm.Meta):
        fields = ['username', 'email', 'phone', 'password']

class SignUpForm(UserCreationForm):
    class Meta(UserCreationForm.Meta):
        model = get_user_model()
        fields = ['username', 'email']