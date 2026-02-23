#!/bin/bash

echo "🔍 Checking port 8080..."

PID=$(lsof -ti :8080)

if [ -z "$PID" ]; then
    echo "✅ Port 8080 is free"
else
    echo "⚠️  Port 8080 is in use by process $PID"
    echo "🔨 Killing process..."
    kill -9 $PID
    echo "✅ Port 8080 is now free"
fi
