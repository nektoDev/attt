#!/usr/bin/env bash

#!/usr/bin/env bash
set -e

RELEASE=$1
if [ -z "$RELEASE" ]
  then
    RELEASE="minor"
fi

echo "Start releasing with $RELEASE bump"
gradle clean build jar bootRepackage docker dockerTag dockerPush dockerPushLatest tag -Prelease -PbumpComponent=$RELEASE