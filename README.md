# BasicGameArena (GitHub edition)

Spring Boot 3.4.5 (Java 21) demo app with a complete CI/CD pipeline using **GitHub Actions → GHCR → Helm → Kubernetes (Minikube)**, plus optional GitOps with Argo CD (in the ops repo).

## Local run
```bash
mvn spring-boot:run
curl http://localhost:8080/api/v1/hello
curl http://localhost:8080/api/v1/metadata
```

## Docker build
```bash
docker build -t bga:dev .
docker run -p 8080:8080 -e COMMIT_SHA=local bga:dev
```

## Helm (local dev)
```bash
helm upgrade --install basic-game-arena ./helm/basic-game-arena   --namespace dev --create-namespace   --set image.repository=ghcr.io/YOUR_GITHUB_USERNAME/basicgamearena-app   --set image.tag=latest   --set-string podAnnotations.app\.git\.commit=local
kubectl -n dev port-forward svc/basic-game-arena 8080:80
curl http://localhost:8080/api/v1/metadata
```

## GitHub Actions
Workflows: **test → build+push (GHCR) → scan → deploy (dev on main)**

**Repository secrets (Settings → Secrets and variables → Actions):**
- `KUBECONFIG_FILE` — base64 of your kubeconfig (gives the workflow access to your cluster).
- (Optional) If pushing to another org’s GHCR, create a PAT with `packages:write`.

## Notes
- Chart includes a `ServiceMonitor` (works if kube-prometheus-stack is installed).
- App exposes `/actuator/prometheus`.

Update: 09/25/2025 14:45:29
