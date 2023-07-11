from django.urls import path

from .views import post_list_view, post_create_view
from .views import post_delete_view, post_detail_view

app_name = 'posts'

urlpatterns = [
    path('', post_list_view, name='post-list'),
    path('new/', post_create_view),
    path('<int:id>/', post_detail_view),
    path('<int:id>/delete', post_delete_view),
]