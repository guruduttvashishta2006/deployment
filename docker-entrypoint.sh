#!/bin/bash
set -e

# Fix Docker socket permissions:
# Match the container's 'docker' group GID to the host's docker socket GID
# so the jenkins user can access /var/run/docker.sock
if [ -S /var/run/docker.sock ]; then
    SOCK_GID=$(stat -c '%g' /var/run/docker.sock)
    CUR_GID=$(getent group docker | cut -d: -f3)
    if [ "$SOCK_GID" != "$CUR_GID" ]; then
        groupmod -g "$SOCK_GID" docker
    fi
fi

# Drop back to jenkins user and start Jenkins
exec su -s /bin/bash jenkins -c "/usr/local/bin/jenkins.sh $*"
