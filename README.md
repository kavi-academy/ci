# CI Repository (Shared Library & Jenkins Terraform)

This repository contains:

1. **Jenkins Shared Library** – a minimal example of a Jenkins Shared Library that can be used by the `Jenkinsfile` in the `app` repo.  The library lives under `shared-lib/` and follows the standard Jenkins structure (`vars/`, `src/`).
2. **Terraform script to provision Jenkins on AWS** – a self‑contained Terraform configuration that creates an EC2 instance running Jenkins, along with the required IAM role, security group, and key pair.  The configuration is placed under `terraform/jenkins/`.

The folder layout:
```
ci/
├─ README.md                         # Overview (this file)
├─ shared-lib/
│   ├─ vars/
│   │   └─ jenkinsDeploy.groovy      # Example Groovy step callable from a Jenkinsfile
│   └─ src/
│       └─ org/
│           └─ example/
│               └─ Utils.groovy    # Sample utility class
├─ terraform/
│   └─ jenkins/
│       ├─ main.tf                  # EC2, IAM, SG, KeyPair resources
│       ├─ variables.tf             # Input variables
│       ├─ outputs.tf               # Useful outputs (instance ID, public IP)
│       └─ backend.tf               # Remote‑state config – copy the snippet from bootstrap
└─ .gitignore
```

You can clone this folder as its own repo (e.g., `git init` inside `ci/`) or keep it as a sub‑module of the main `infra` repo.  The shared library can be referenced by Jenkins via the **‘Pipeline Shared Libraries’** configuration, and the Terraform script can be invoked from the Jenkins pipeline to spin up the Jenkins server itself (useful for a fresh sandbox).
