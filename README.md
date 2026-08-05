# Enterprise DevOps Project

## Overview

This repository demonstrates an end-to-end Enterprise CI/CD pipeline.

## Technologies

- Git
- GitHub
- Jenkins
- Maven
- Docker
- Kubernetes
- Terraform
- AWS
- SonarQube
- Trivy
- ArgoCD
- Prometheus
- Grafana

## Pipeline

Developer

↓

GitHub

↓

Jenkins

↓

Docker

↓

Kubernetes

↓

Production

# Employee Service - Quick Reference

## Application

http://localhost:8081/employees

## Health Check

http://localhost:8081/actuator/health

## Application Information

http://localhost:8081/actuator/info

## Jenkins Pipeline

Checkout
↓

Build
↓

Archive
↓

Docker Build
↓

Docker Push
↓

Deploy
↓

Health Check
↓

Smoke Test
↓

Deployment Summary
↓

Docker Cleanup

## Docker Commands

View Containers

docker ps

View Logs

docker logs employee-service

Restart Container

docker restart employee-service

Stop Container

docker stop employee-service

Remove Container

docker rm -f employee-service