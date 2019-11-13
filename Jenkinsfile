pipeline {
    agent {
        docker {
            image 'openjdk'
        }
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'javac HelloWorld.java && java HelloWorld'
            }
        }
       
    }
}
