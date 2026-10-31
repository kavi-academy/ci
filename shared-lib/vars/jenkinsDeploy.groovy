# Shared Library – vars/jenkinsDeploy.groovy

def call(Map args) {
    // args: environment (dev|test|uat|prod), imageTag (Docker tag)
    def env = args.environment ?: 'dev'
    def tag = args.imageTag ?: 'latest'
    echo "Deploying ${env} environment with image tag ${tag}"

    // Assume the Terraform code for the target environment lives in a sibling directory
    // Change to the infra repo (could be a submodule) and run terraform apply
    dir('../infra/environments/${env}') {
        // Pass the image tag as a TF variable via -var
        sh "terraform init -reconfigure"
        sh "terraform apply -auto-approve -var='docker_image_tag=${tag}'"
    }
}
