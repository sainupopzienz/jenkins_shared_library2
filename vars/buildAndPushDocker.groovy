def call() {
    // Git clone
    git 'https://github.com/your/repository.git'

    // Maven build
    sh "mvn clean test package"

    // Docker build and push
    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        sh "docker build -t my-image ."
        sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
        sh "docker push my-image"
    }
}
