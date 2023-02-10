def call(Map config=[:]) {
node {
    stage('SCM') {
	echo 'Gathering code from version control'
    git branch: '${branch}', url: 'https://github.com/petercharlespcg/JenkinsGroovy.git'
    }
    stage('Build') {
        try {
            echo 'Building....'
            // sh 'dotnet --version'
            sh 'pwd'
            sh 'rm -rf ConsoleApp1/bin'
            sh 'rm -rf ConsoleApp1/obj'
            sh 'dotnet build ' + config.target
            echo 'Building peter new feature'
            releasenotes(changes:"true")
        } catch (ex) {
            echo 'Something went wrong'
            echo ex.toString();
            currentBuild.result = 'FAILURE'
        }
        finally {
            // cleanup
        }
    }
    stage('Test') {
        echo 'Testing....'
    }
    stage('Deploy') {
        echo 'Deploying....'
    }
}
}
