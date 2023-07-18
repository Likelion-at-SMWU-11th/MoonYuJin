from django.shortcuts import render

from django.views.generic import ListView
from .models import Post

class class_view(ListView):
    model = Post
    template_name = 'index.html'

def function_view(request):
    print(f'request.method: {request.method}')

    if request.method == "GET":
        print(f'request.GET: {request.GET}')
    elif request.method == 'POST':
        print(f'request.POST: {request.POST}')
    return render(request, 'view.html')
