pipeline {
    agent any	
    stages {
        stage('Utility Steps method') {
            steps {
                script {
                    def files = findFiles(glob: '**/*.log')
                    echo files[0].name
                    // echo files[1].name
                }
            }
        }
    }
}