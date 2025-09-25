{{- define "basic-game-arena.name" -}}
basic-game-arena
{{- end -}}

{{- define "basic-game-arena.fullname" -}}
{{ include "basic-game-arena.name" . }}
{{- end -}}
