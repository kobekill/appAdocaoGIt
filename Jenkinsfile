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
                sh 'mvn -B -DskipTest clean package'
            }
        }
       
    }
}
