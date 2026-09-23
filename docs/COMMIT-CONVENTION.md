# Commit convention

[Conventional Commits](https://www.conventionalcommits.org/). Format:

```
<type>(<scope>): <subject>

[optional body — the WHY, not the what]
```

## Rules

- **Subject**: imperative mood, lowercase, no trailing period, <= 72 characters.
  It must complete the sentence *"If applied, this commit will ..."*.
- **One logical change per commit.** Many small commits, never one large push.
- **Body** only when the "why" isn't obvious from the subject.
- Never commit secrets, `.env` files, credentials, or `terraform.tfstate`.

## Types

| Type | Use for |
|---|---|
| `feat` | A new user-facing capability |
| `fix` | A bug fix |
| `docs` | Documentation only |
| `chore` | Tooling, config, housekeeping |
| `refactor` | Code change that neither fixes a bug nor adds a feature |
| `test` | Adding or correcting tests |
| `ci` | CI/CD pipeline changes |
| `build` | Build system, dependencies, Dockerfiles |
| `perf` | Performance improvement |
| `style` | Formatting only, no logic change |
| `revert` | Reverts a previous commit |

## Scopes

`backend` `frontend` `db` `docker` `scripts` `ci` `terraform` `k8s` `docs` `repo`

## Examples

```
feat(backend): add stock holding CRUD endpoints
fix(scripts): handle missing pg_dump in backup-db.sh
ci(github): cache maven dependencies between runs
docs(readme): add architecture diagram
chore(repo): add editorconfig and gitattributes
build(docker): switch backend image to multi-stage build
```

## Why this matters here

This repository is a public portfolio piece. The commit history is part of what a
reviewer sees. A graph showing sustained incremental work over months reads as
genuine engineering; a single `initial commit` of 4,000 lines reads as a tutorial
that was copied. The history is evidence, so it is written deliberately.
