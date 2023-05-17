from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.

def myStudy(request):
    # return HttpResponse('내가 원하는 스터디')
    return render(request, 'studyDjango.html')
