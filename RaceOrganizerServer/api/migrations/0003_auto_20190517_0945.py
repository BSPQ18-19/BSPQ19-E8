# Generated by Django 2.2 on 2019-05-17 09:45

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):
    dependencies = [
        ('api', '0002_task'),
    ]

    operations = [
        migrations.AlterField(
            model_name='task',
            name='person',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.CASCADE,
                                    to='api.Person'),
        ),
    ]
