from django.contrib import admin
from django.conf import settings
from django.conf.urls.static import static
from django.urls import path, include
from rest_framework import routers
from posts.views import *

router=routers.DefaultRouter()
router.register('posts',PostModelViewSet)

urlpatterns = [
    path('', include(router.urls)),  # 맨 위에 써야 제대로 동작
    path('admin/', admin.site.urls),
    # path('', index, name='index'),
    # path('url/', url_view),
    # path('url/<str:username>/', url_parameter_view),
    # path('fbv/', function_view),

    # # Class Based View
    # path('cbv/', class_view.as_view()), # as_view: 진입 메소드

    # # path('posts/', include('posts.urls', namespace='posts')),
    # # path('posts/<int:id>/', include(router.urls)),
    # path('accounts/', include('accounts.urls', namespace='accounts')),
    # path('calculator/', calculator, name='calculator'),
    
    # path('posts/', PostListCreateView.as_view(), name='post-list'),
    # path('posts/<int:pk>/', PostRetrieveUpdateView.as_view(), name='post-detail'),
]

urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)