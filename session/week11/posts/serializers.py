from rest_framework.serializers import ModelSerializer
from .models import Post, Comment

class PostModelSerializer(ModelSerializer):
    class Meta:
        model=Post
        fields='__all__'
        #fields=['id', 'writer', 'content']

class PostListSerializer(PostModelSerializer):
    class Meta(PostModelSerializer.Meta):
        fields = [
            'id',
            'image',
            'content',
            'created_at',
            'view_count',
            'writer',
        ]
        depth = 1  # 댓글 구현할 때 자주 사용

class PostCreateSerializer(PostModelSerializer):
    class Meta(PostModelSerializer.Meta):
        fields = [
            'image',
            'content',
        ]

class PostRetrieveSerializer(PostModelSerializer):
    class Meta(PostModelSerializer.Meta):
        depth = 1

class CommentListModelSerializer(ModelSerializer):
    class Meta:
        model = Comment
        fields = '__all__'