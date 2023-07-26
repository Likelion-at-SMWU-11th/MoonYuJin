from django.contrib import admin
from posts.models import Post, Comment

# Register your models here.
# admin.site.register(Post)
admin.site.register(Comment)

class CommentInline(admin.TabularInline):
    model = Comment
    extra = 1
    min_num = 1
    max_num = 1
    verbose_name = '댓글'
    verbose_name_plural = '댓글'

@admin.register(Post)
class PostModelAdmin(admin.ModelAdmin):
    list_display = ['id', 'image', 'content', 'created_at', 'view_count', 'writer']
    list_editable = ['content', 'writer']
    list_filter = ['created_at']
    search_fields = ['id', 'writer__username']
    search_help_text = '게시판 번호, 작성자 검색이 가능합니다'
    readonly_fields = ['view_count', 'created_at']
    inlines = [CommentInline,]

    actions = ['make_published']

    def make_published(modeladmin, request, queryset):
        for item in queryset:
            item.content = '운영규칙 위반으로 인한 게시글 삭제 처리'
            item.save()