from django.contrib import admin

from api.models import *


class PersonAdmin(admin.ModelAdmin):
    list_display = ('personal_id', 'get_username', 'get_email',)
    list_display_links = ('get_username',)


class RaceAdmin(admin.ModelAdmin):
    list_display = ('pk', 'edition', 'place', 'time', 'organizer',)
    list_display_links = ('edition',)


class RunnerAdmin(admin.ModelAdmin):
    list_display = ('person', 'race', 'number')
    list_display_links = ('person',)


class TaskAdmin(admin.ModelAdmin):
    list_display = ('race', 'person', 'description', 'completed')
    list_display_links = ('description',)


admin.site.register(Person, PersonAdmin)
admin.site.register(Race, RaceAdmin)
admin.site.register(Runner, RunnerAdmin)
admin.site.register(Task, TaskAdmin)
