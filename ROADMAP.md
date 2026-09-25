# ROADMAP

Single source of truth for **where this project is**. Updated as each phase lands.

**Status legend:** ⬜ not started · 🟨 in progress · ✅ done

| Phase | Title | Focus | Status |
|---|---|---|---|
| 0 | Repo & workflow foundations | Git, GitHub, README skeleton | ✅ |
| 1 | Spring Boot API skeleton (stocks) | Java, REST, tests | ⬜ |
| 2 | PostgreSQL + Docker & Compose | Docker, images, networks, volumes | ⬜ |
| 3 | Bash operational tooling | Linux, shell, processes, signals | ⬜ |
| 4 | GitHub Actions CI | CI, testing, linting, scanning | ⬜ |
| 5 | Angular frontend + dashboard | Frontend, nginx, first screenshot | ⬜ |
| 6 | Crypto + savings + consolidation | Domain completion, external APIs | ⬜ |
| 7 | Terraform + LocalStack | **IaC from zero** | ⬜ |
| 8 | Kubernetes on kind | **K8s from zero** | ⬜ |
| 9 | Continuous deployment to kind | CD, Kustomize, GitOps-lite | ⬜ |
| 10 | Observability & final polish | Metrics, docs, portfolio finish | ⬜ |

**Estimated total:** ~60–75 sessions of 1 hour (~3–4 months at 5 days/week).

**CV checkpoint:** the repo is worth linking on a CV **after Phase 5** at the earliest
(a recruiter can see a screenshot, a green pipeline and a one-command run). It becomes
genuinely strong for a *cloud/platform* role **after Phase 8**, because that is the
phase where Terraform and Kubernetes are both real and visible. See "CV readiness" below.

---

## Phase 0 — Repo & workflow foundations
*~3 sessions · no application code*

**Build**
- Verify `git config user.email` matches the GitHub account email; fix if not.
- `git init`, first commit, public GitHub repo via `gh repo create`, description + topics.
- `.gitignore`, `LICENSE` (MIT), `.editorconfig`, `.gitattributes`.
- README skeleton with every section headed and a "Status: in progress" banner.
- `docs/README-STRUCTURE.md`, `docs/COMMIT-CONVENTION.md`.

**Learn**
- How GitHub attributes commits (email matching, not username).
- Conventional Commits and why commit history is a portfolio artifact.
- Why the README gets written first, not last.

**Done when**
- Public repo exists with topics set; commits show as green on the profile.
- README renders with all headings present; roadmap linked.
- `git log` shows ≥4 small, conventional commits.

---

## Phase 1 — Spring Boot API skeleton (stocks only)
*~5 sessions*

**Build**
- Spring Boot project (Maven, Java 21) — `holdings` REST resource for stocks only.
- Entity, repository, service, controller; H2 in-memory to start.
- Bean validation, `@RestControllerAdvice` error handling, Spring Actuator health endpoint.
- Unit tests (JUnit 5 + Mockito) and `@WebMvcTest` slice tests.

**Learn**
- Layered architecture and why the service layer exists.
- DTO vs entity, and why leaking entities over HTTP is a trap.
- Actuator `/health` — the same endpoint Kubernetes probes will call in Phase 8.

**Done when**
- `mvn test` green; `curl localhost:8080/api/holdings` returns JSON.
- `/actuator/health` returns `UP`.
- README "Tech stack" and "Run locally" sections updated.

---

## Phase 2 — PostgreSQL + Docker & Docker Compose
*~5 sessions*

**Decision up front:** Docker Desktop (WSL2 integration) **or** Docker Engine directly
in WSL. Explained and chosen at the start of this phase.

**Build**
- Swap H2 → PostgreSQL; Flyway migrations for schema.
- Multi-stage `Dockerfile` for the backend (build stage + slim JRE runtime, non-root user).
- `docker-compose.yml`: postgres + backend, healthchecks, named volume, `.env.example`.
- `.dockerignore`.

**Learn**
- Images vs containers vs layers; why multi-stage builds shrink images and attack surface.
- Compose networking and DNS by service name; volumes and data persistence.
- Why containers run as non-root.
- Port forwarding from WSL to the Windows browser — explained properly, first time.

**Done when**
- `docker compose up` brings up a working API backed by Postgres from a cold start.
- Data survives `docker compose restart`; `docker compose down -v` wipes it.
- Backend image under ~250 MB.
- README gains an architecture diagram and a real one-command run.

---

## Phase 3 — Bash operational tooling
*~6 sessions · the deliberate Linux/shell phase*

**Build** — `scripts/`, each with `set -euo pipefail`, `usage()`, arg parsing, `trap`:
- `setup.sh` — verify/install prerequisites, check versions, create `.env`.
- `seed-db.sh` — load realistic sample portfolio data.
- `backup-db.sh` / `restore-db.sh` — `pg_dump`/`pg_restore`, timestamped, rotation.
- `health-check.sh` — poll endpoints, meaningful exit codes, retry with backoff.
- `collect-logs.sh` — gather container logs into a timestamped tarball.
- `teardown.sh` — clean shutdown with a confirmation prompt and a `--force` flag.

**Learn**
- `set -euo pipefail` clause by clause, and where it bites.
- Exit codes and what a caller does with them; `trap` and signal handling (SIGINT/SIGTERM).
- Processes, foreground/background, file permissions and ownership, users/groups.
- `getopts` vs manual argument parsing.
- **Quiz:** you write `health-check.sh` before seeing my version.

**Done when**
- All scripts pass `shellcheck` with zero warnings.
- Every script runs correctly with `--help` and with bad arguments.
- `backup-db.sh` → `teardown.sh` → `setup.sh` → `restore-db.sh` round-trips the data.
- README "Operational scripts" table added.

---

## Phase 4 — GitHub Actions CI
*~6 sessions*

**Build**
- `ci.yml`: build + unit tests, Maven caching, matrix where it earns its place.
- Integration tests against real Postgres (Testcontainers or a service container).
- Linting: Checkstyle/Spotless (Java), `shellcheck` (scripts), `hadolint` (Dockerfile).
- Security: Trivy image scan + `dependency-review` + Dependabot config.
- Build and push image to GitHub Container Registry (free for public repos).
- Status badges in the README.

**Learn**
- Actions vs GitLab CI: jobs/steps/runners, `actions/*` marketplace, the YAML differences.
- How GitHub Actions **secrets** work — masking, scoping, why `pull_request_target` is dangerous.
- Caching strategies and why CI minutes matter on a free tier.
- Reading a security scan report and deciding what actually matters.

**Done when**
- Green pipeline on every push and PR; a deliberately broken test makes it red.
- Badges live in the README.
- No secret ever appears in a log.

---

## Phase 5 — Angular frontend + dashboard
*~6 sessions*

**Build**
- Angular app: holdings list, add/edit form, and a first dashboard (total value, P&L).
- One chart (allocation breakdown).
- Multi-stage Dockerfile → nginx; wired into Compose; CORS handled properly.
- **First screenshot/GIF of the running dashboard** committed to `docs/images/`.

**Learn**
- Serving a SPA from nginx and why the fallback route is needed.
- Reverse-proxying `/api` to avoid CORS entirely — and the trade-off vs. CORS headers.

**Done when**
- `docker compose up` → full stack at one URL, reachable from the Windows browser.
- Screenshot is at the top of the README.
- ⭐ **First CV-linkable checkpoint.**

---

## Phase 6 — Crypto + savings + consolidated dashboard
*~5 sessions*

**Build**
- Crypto holdings with live prices from a free public API (no key / no card).
- Savings accounts with interest-rate projection.
- Consolidated net worth, allocation across all three types, performance over time.
- Caching + timeout + fallback for the external API; scheduled price refresh.

**Learn**
- Calling an unreliable third party without making your own API unreliable
  (timeouts, caching, graceful degradation).
- Why financial values use `BigDecimal`, never `double`.

**Done when**
- Dashboard shows all three asset types consolidated.
- The app still works when the price API is unreachable.
- Integration tests cover the failure path.

---

## Phase 7 — Terraform + LocalStack
*~8 sessions · **learning from zero***

**Build**
- LocalStack community edition in Compose.
- Terraform from first principles against LocalStack: S3 bucket for DB backups,
  IAM policy/role, Secrets Manager entry, SNS/SQS for a notification path.
- Remote state in a LocalStack S3 backend.
- Modules, variables, outputs; `dev` workspace; `terraform fmt`/`validate` in CI.
- `backup-db.sh` extended to upload to the S3 bucket.

**Learn**
- Declarative vs imperative; what state *is* and why it's the hardest part of Terraform.
- Plan/apply cycle, drift, `terraform import`, why you never edit state by hand.
- Providers, resources, data sources, modules; variable precedence.
- LocalStack's limits and how I'd honestly describe this in an interview.

**Done when**
- `terraform apply` from zero creates all resources in LocalStack, idempotently.
- `terraform destroy` removes everything; re-apply reproduces it exactly.
- A `plan` on unchanged code reports no changes.
- `docs/terraform.md` explains the design; README updated.

---

## Phase 8 — Kubernetes on kind
*~10 sessions · **learning from zero***

**Build**
- kind cluster (multi-node) via a config file; `kubectl` context management.
- Manifests: Deployments, Services, ConfigMaps, Secrets, PVC for Postgres, Ingress.
- Liveness/readiness/startup probes hitting Actuator; resource requests and limits.
- nginx-ingress; a single hostname for frontend + API.
- Kustomize base + overlay.
- `scripts/kind-up.sh` / `kind-down.sh`.

**Learn**
- Pods, ReplicaSets, Deployments, Services, Ingress — and why each layer exists.
- Declarative reconciliation: the control loop, desired vs actual state.
- **Secrets are base64, not encrypted** — what that means and the real alternatives.
- Probes, rolling updates, and how a bad readiness probe causes an outage.
- Debugging: `describe`, `logs`, `exec`, `port-forward`, events.
- WSL memory tuning via `.wslconfig` if the cluster is resource-starved.

**Done when**
- `./scripts/kind-up.sh` builds a cluster and deploys the whole stack from scratch.
- App reachable from the Windows browser through Ingress.
- Killing a pod self-heals; a rolling update ships with zero downtime.
- `docs/kubernetes.md` written; README architecture diagram shows the cluster.
- ⭐ **Strong CV checkpoint — Terraform and Kubernetes both real.**

---

## Phase 9 — Continuous deployment to kind
*~5 sessions*

**Build**
- CD workflow: build → scan → push to GHCR → deploy to kind via a self-hosted runner
  (or a documented, scripted local `deploy.sh` — decided in-phase, zero-cost either way).
- Image tagging by commit SHA; automated rollback on failed rollout.
- `scripts/deploy.sh` and `scripts/rollback.sh`.

**Learn**
- Why `:latest` is an anti-pattern; immutable tags.
- `kubectl rollout status` / `undo` as a deployment gate.
- Push vs pull deployment models (and where ArgoCD would fit).

**Done when**
- A merge to `main` results in a deployed, running new version.
- A deliberately broken image triggers an automatic rollback.

---

## Phase 10 — Observability & final polish
*~6 sessions*

**Build**
- Prometheus + Grafana in-cluster (free, self-hosted); Micrometer metrics from Spring Boot.
- One meaningful dashboard; structured JSON logging.
- Final README pass: diagram, GIF, badges, "what I learned", "what I'd do differently".
- `docs/architecture.md`, `docs/runbook.md`.
- Repo pinned on the GitHub profile; LinkedIn featured link.

**Done when**
- Grafana shows live application metrics.
- README passes the 30-second test on a fresh reader.
- ⭐ **Project complete and presentable.**

---

## CV readiness

| Point | Verdict |
|---|---|
| Before Phase 5 | **Do not link it.** No visible product, no green pipeline — it reads as abandoned. |
| After Phase 5 | Linkable. Screenshot + CI badge + one-command run clears the 30-second bar. |
| After Phase 8 | **Strong.** This is when it supports a cloud/platform application honestly. |
| After Phase 10 | Complete. Pin it, feature it on LinkedIn, lead with it. |

---

## Session log

Add a line per session. Honest record, including the sessions that went nowhere.

| Date | Phase | What happened |
|---|---|---|
| 2026-09-23 | — | Planning: CLAUDE.md + ROADMAP.md written. Awaiting go-ahead for Phase 0. |
| 2026-09-23 | 0 | `git init -b main`, 5 conventional commits, public repo + topics via `gh`. Push hit GH007 (private email) → switched to noreply address, rewrote authors with `rebase --exec --reset-author`. Unignored `.terraform.lock.hcl`; CLAUDE.md kept local. ✅ Phase 0 done. |
