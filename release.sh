#!/usr/bin/env bash

#!/usr/bin/env bash
set -e

RELEASE=$1
if [ -z "$RELEASE" ]
  then
    RELEASE="minor"
fi

echo "Start releasing with $RELEASE bump"
gradle clean
gradle build -Prelease -PbumpComponent=$RELEASE
gradle jar
gradle bootRepackage
gradle docker
gradle dockerTag
gradle dockerPush
gradle dockerPushLatest
gradle tag
gradle printVersion
echo "Release finished"