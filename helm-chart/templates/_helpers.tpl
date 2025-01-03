{{/*
Expand the name of the chart.
*/}}
{{- define "popularity-service.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "popularity-service.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- if .Values.canary }}
{{- printf "%s-canary" .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "popularity-service.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{ define "popularity-service.env "}}
{{- printf "%s" .Chart.Name | replace "-" "_" | trunc 63 | upper }}
{{- end }}}

{{/*
Common labels
*/}}
{{- define "popularity-service.labels" -}}
helm.sh/chart: {{ include "popularity-service.chart" . }}
application.giantswarm.io/team: {{ index .Chart.Annotations "application.giantswarm.io/team" | quote }}
app.kubernetes.io/version: {{ default .Values.image.tag .Chart.AppVersion | quote }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{ include "popularity-service.selectorLabels" . }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "popularity-service.selectorLabels" -}}
app.kubernetes.io/name: {{ include "popularity-service.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}


{{- define "popularity-service.deployment" -}}
{{ if or (eq .Release.Name "popularity-service") (eq .Release.Name "popularity-service-canary") }}
popularity-service.deployment: main
{{ else }}
popularity-service.deployment: {{ .Release.Name }}
{{- end }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "popularity-service.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "popularity-service.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}
