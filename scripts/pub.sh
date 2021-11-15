#!/usr/bin/env bash

filepath=$(cd "$(dirname "$0")"; pwd)
${filepath}/build.sh && ${filepath}/deliver.sh