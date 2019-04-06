from django.contrib import admin

from api.models import User


class UserAdmin(admin.ModelAdmin):
    list_display = ('personal_id', 'username', 'email',)
    list_display_links = ('username',)


admin.site.register(User, UserAdmin)
