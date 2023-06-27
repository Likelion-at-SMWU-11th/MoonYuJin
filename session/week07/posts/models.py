from django.db import models

# Create your models here.

class Post (models.Model):
    image = models.ImageField(verbose_name='이미지')
    contentn = models.TextField('내용')
    created_at = models.DateTimeField('작성일')
    view_count = models.IntegerField('조회수')
