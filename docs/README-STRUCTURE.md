# README structure

The README is optimised for a reader who gives the repository **30 seconds** and
never scrolls past the fold. Section order is fixed.

1. **Title + one-line description** — what it is and why it exists.
2. **Badges** — CI, CD, license. (Phase 4)
3. **Screenshot / GIF of the running dashboard** — above the fold. (Phase 5)
4. **What this is** — 2-3 sentences, including the zero-cost design.
5. **Architecture** — diagram first, then a short paragraph.
6. **Tech stack** — compact table: layer -> technology -> why chosen.
7. **Run it locally** — a genuine one-command start from a cold clone.
8. **CI/CD pipeline** — what each stage does.
9. **Kubernetes & Terraform** — short, linking out to `docs/`.
10. **Operational scripts** — table: script -> what it does.
11. **What I learned** — honest and specific.
12. **Roadmap** — link to `ROADMAP.md`.
13. **License**

## Three rules

1. **The screenshot goes above the fold.** Most repositories are a wall of text.
   An image proves the thing runs, in under a second, before any reading happens.
2. **The one-command run must actually work from a cold clone.** If a reviewer
   tries it and it fails, that is worse than having no README at all. It is
   re-verified at the end of every phase that touches it.
3. **"What I learned" is not filler.** For a career-changer it is the highest-value
   section in the file — the only place judgement shows rather than output.
   It is appended to as each phase lands, never written at the end.

## Maintenance rule

The README is updated **as part of each phase**, not afterwards. A phase is not
done until the README reflects it.
