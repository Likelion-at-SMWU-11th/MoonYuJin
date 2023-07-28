from django import forms
from .models import Post

# form (일반 폼)
class PostBasedForm(forms.ModelForm):
    """ CATEGORY_CHOICES=[
        ('1', '일반'),
        ('2', '계정'),
    ]
    image = forms.ImageField(label='이미지')
    content = forms.CharField(label='내용', widget=forms.Textarea)
    category = forms.ChoiceField(label='카테고리', choices=CATEGORY_CHOICES) """
    class Meta:
        model = Post
        fields = '__all__'

class PostCreateForm(PostBasedForm):
    class Meta(PostBasedForm.Meta):
        fields = ["image", 'content']

# Model Form (모델 폼)
class PostCreateForm(PostBasedForm):
    class Meta(PostBasedForm.Meta):
        fields = ['image', 'content'] #'__all__' 설정시 전체 필드 추가

class PostUpdateForm(PostBasedForm):
    class Meta(PostBasedForm.Meta):
        fields = ['image', 'content']

class PostDetailForm(PostBasedForm):
    def __init__(self, *args, **kwargs):
        super(PostDetailForm, self).__init__(*args, **kwargs)
        for key in self.fields:
            self.fields[key].widget.attrs['disabled'] = True