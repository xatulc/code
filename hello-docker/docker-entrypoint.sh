#! /bin/bash
set -e

SERVICE_NAME="hello-docker"

if [ "$1" = "run-${SERVICE_NAME}" ]; then

    # skywalking 开关开启且 agent 目录存在
    if [[ "$SW_AGENT_ROOT" != "" ]] && [[ "$SW_ENABLE" == "true" ]]; then
        AGENT_PATH="$SW_AGENT_ROOT/skywalking-agent.jar"
        # skywalking-agent.jar agent jar 包存在
        if [ -f "$AGENT_PATH" ]; then
            echo "<<<<<<<<<<<<< run ${SERVICE_NAME} with skywalking agent >>>>>>>>>>>>>>>>>>>"
            java $JAVA_OPTS -javaagent:$AGENT_PATH org.springframework.boot.loader.JarLauncher
            exit
        fi
    fi

    echo "<<<<<<<<<<<< run default ${SERVICE_NAME} >>>>>>>>>>>>>>>>>>>>>"
    java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
    exit
fi

echo "<<<<<<<<<<<< run overwrite command: $@ >>>>>>>>>>>>>>>>>>>>>"
exec "$@"