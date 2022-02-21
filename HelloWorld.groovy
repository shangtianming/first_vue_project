println env.JOB_NAME
println env.BUILD_NUMBER
println env.WORKSPACE

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