from django.contrib import admin
from django.conf import settings
from django.conf.urls.static import static
from django.urls import path, include
from rest_framework import routers
from posts.views import *

from accounts_token.views import login_view

router=routers.DefaultRouter()
router.register('posts',PostModelViewSet)


from django.urls import re_path
from rest_framework import permissions
from drf_yasg.views import get_schema_view
from drf_yasg import openapi

schema_view = get_schema_view(
    openapi.Info(
        title = "Snippets API",
        default_version = 'v1',
        description = "Test description",
        terms_of_service = "https://www.google.com/policies/terms/",
        contact = openapi.Contact(email="contact@snippets.local"),
        license = openapi.License(name="BSD License"),
    ),
    public=True,
    permission_classes=(permissions.AllowAny,),
)



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

    # 토큰 인증 로그인 구현
    path('login/', login_view),

    # swagger
    path('swagger<format>/', schema_view.without_ui(cache_timeout=0), name='schema-json'),
    path('swagger/', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
    path('redoc/', schema_view.with_ui('redoc', cache_timeout=0), name='schema-redoc'),

]

urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)