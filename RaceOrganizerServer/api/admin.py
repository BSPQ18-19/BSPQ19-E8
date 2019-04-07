from django.contrib import admin

from api.models import *


class PersonAdmin(admin.ModelAdmin):
    list_display = ('personal_id', 'username', 'email',)
    list_display_links = ('username',)


class RaceAdmin(admin.ModelAdmin):
    list_display = ('race_id', 'edition', 'place', 'time', 'organizer',)
    list_display_links = ('edition',)


class RunnerAdmin(admin.ModelAdmin):
    list_display = ('person', 'race', 'number')
    list_display_links = ('person',)


admin.site.register(Person, PersonAdmin)
admin.site.register(Race, RaceAdmin)
admin.site.register(Runner, RunnerAdmin)
