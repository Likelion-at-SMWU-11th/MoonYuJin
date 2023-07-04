from django.contrib import admin
from .models import Post
from .models import Blog

admin.site.register(Post)
admin.site.register(Blog)

# Register your models here.
