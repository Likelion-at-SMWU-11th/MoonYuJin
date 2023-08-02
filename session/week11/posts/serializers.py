from rest_framework.serializers import ModelSerializer
from .models import Post

class PostModelSerializer(ModelSerializer):
    class Meta:
        model=Post
        fields='__all__'
        #fields=['id', 'writer', 'content']

class PostDetailModelSerializer(ModelSerializer):
    class Meta:
        model=Post
        fields='__all__'