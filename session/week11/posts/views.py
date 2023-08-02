from django.shortcuts import render, redirect, get_object_or_404
from django.http import HttpResponse, JsonResponse, Http404
from django.views.generic import ListView
from .models import Post
from .forms import PostBasedForm, PostCreateForm, PostUpdateForm, PostDetailForm
from django.contrib.auth.decorators import login_required
from rest_framework.viewsets import ModelViewSet
from .serializers import PostModelSerializer, PostDetailModelSerializer
from rest_framework.response import Response
from rest_framework.decorators import api_view

def index(request):
    post_list = Post.objects.all().order_by('-created_at')
    context = {
        'post_list':post_list,
    }
    return render(request, 'index.html', context)

def post_list_view(request):
    ## admin 계정 유저의 글만 보임
    # post_list = Post.objects.filter(writer=request.user)
    post_list = Post.objects.all()
    context = {
        'post_list':post_list,
    }
    return render(request, 'posts/post_list.html', context)

def post_detail_view(request, id):
    try:
        post = Post.objects.get(id=id)
    except Post.DoesNotExist:
        return redirect('index')
    post = Post.objects.get(id=id)
    context = {
        'post': post,
        'form': PostDetailForm(),
    }
    return render(request, 'posts/post_detail.html', context)

@login_required
def post_create_view(request):
    if request.method == 'GET':
        return render(request, 'posts/post_form.html')
    else:
        image = request.FILES.get('image')
        content = request.POST.get('content')
        Post.objects.create(
            image = image,
            content = content,
            writer = request.user
        )
        return redirect('index')

def post_create_form_view(request):
    if request.method == "GET":
        form = PostCreateForm()
        context = {
            'form': form
        }
        return render(request, 'posts/post_form2.html', context)
    else:
        form = PostCreateForm(request.POST, request.FILES)

        if form.is_valid():
            Post.objects.create(
                image = form.cleaned_data['image'],
                content = form.cleaned_data['content'],
                writer = request.user
            )
        else:
            return redirect('post:post-create')
        return redirect('index')

@login_required
def post_update_view(request, id):
    # post = Post.objects.get(id=id)
    post = get_object_or_404(Post, id=id, writer=request.user)
    if request.method == 'GET':
        context = {
            'post': post
        }
        return render(request, 'posts/post_form.html', context)
    elif request.method == 'POST':
        new_image = request.FILES.get('image')
        content = request.POST.get('content')

        if new_image:
            post.image.delete()
            post.image = new_image
        post.content = content
        post.save()
        return redirect('posts:post-detail', post.id)

@login_required
def post_delete_view(request, id):
    post = get_object_or_404(Post, id=id, writer=request.user)
    ## 위의 writer=request.user와 같은 역할
    # if request.user != post.writer:
    #     return Http404('잘못된 접근입니다.')
    if request.method == 'GET':
        context = {
            'post':post
        }
        return render(request, 'posts/post_confirm_delete.html', context)
    else:
        post.delete()
        return render('index')

class class_view(ListView):
    model = Post
    template_name = 'cbv_view.html'

def url_view(request):
    data = {'code': '001', 'msg': 'OK'}
    return HttpResponse('<h1>url_views</h1>')

def url_parameter_view(request, username):
    print(f'url_parameter_view()')
    print(f'username: {username}')
    print(f'request.GET: {request.GET}')
    return HttpResponse(username)

def function_view(request):
    print(f'request.method: {request.method}')

    if request.method == "GET":
        print(f'request.GET: {request.GET}')
    elif request.method == 'POST':
        print(f'request.POST: {request.POST}')
    return render(request, 'view.html')

class PostModelViewSet(ModelViewSet):
    queryset = Post.objects.all()
    serializer_class = PostModelSerializer

class PostDetailModelViewSet(ModelViewSet):
    queryset = Post.objects.all()
    serializer_class = PostDetailModelSerializer

@api_view()
def post_detail_update_or_delete(request, id):
    post = get_object_or_404(Post, id=id)
    if request.method == 'PUT':
        serializer_class = PostModelSerializer(instance=post, data=request.data)
        if serializer_class.is_valid():
            serializer_class.save()
    elif request.method == 'DELETE':
        post.delete()
        data = {
            'post': id
        }
    return Response(data)

@api_view()
def calculator(request):
    num1 = request.GET.get('num1',0)
    num2 = request.GET.get('num2',0)
    operators = request.GET.get('operators')

    if operators == '^':  # '+'기호는 규칙상 사용불가하므로 다른 기호 넣기
        result = int(num1) + int(num2)
    elif operators == '-':
        result = int(num1) - int(num2)
    elif operators == '*':
        result = int(num1) * int(num2)
    elif operators == '/':
        result = int(num1) / int(num2)
    else:
        result = 0
    
    data = {
        'type': 'FBW',
        'result': result
    }
    return Response(data)