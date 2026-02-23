# 🧪 MANUAL TESTING GUIDE

## 1️⃣ LOGOUT AND LOGIN (For Docker Fix)

### Option A: Logout from GUI
1. Click your username in top-right corner
2. Click "Log Out"
3. Log back in with your password

### Option B: Restart Terminal Session
```bash
# Close current terminal and open a new one
# OR use this command:
newgrp docker
```

### Option C: Reboot (Most reliable)
```bash
sudo reboot
```

After login, verify Docker works:
```bash
docker ps
```
If no error, Docker is ready! ✅

---

## 2️⃣ MANUAL TESTING STEPS

### Test 1: Build the Project
```bash
cd ~/nexus
mvn clean package
```
**✅ Success:** See `BUILD SUCCESS`

### Test 2: Run Tests
```bash
mvn test
```
**✅ Success:** See `Tests run: 1, Failures: 0`

### Test 3: Check WAR File
```bash
ls -lh target/nexus-web-app-*.war
```
**✅ Success:** File exists (~17MB)

### Test 4: Run Application
```bash
mvn spring-boot:run
```
**✅ Success:** See "Started NexusWebApplication"

**Keep this terminal open!**

### Test 5: Test Endpoints (New Terminal)

Open a **NEW terminal** and run:

```bash
# Test home endpoint
curl http://localhost:8080/

# Expected output:
# "Welcome to Nexus Artifact Management Demo Application!"

# Test version endpoint
curl http://localhost:8080/version

# Expected output:
# "Version: 1.0.0-SNAPSHOT"

# Test health endpoint
curl http://localhost:8080/health

# Expected output:
# "Application is running"
```

**✅ Success:** All 3 endpoints respond correctly

**Stop the app:** Go back to first terminal, press `Ctrl+C`

### Test 6: Build Docker Image
```bash
docker build -t nexus-web-app:test .
```
**✅ Success:** See "Successfully built"

### Test 7: Run Docker Container
```bash
# Run container
docker run -d -p 8080:8080 --name test-app nexus-web-app:test

# Test it
curl http://localhost:8080/

# Check logs
docker logs test-app

# Stop and remove
docker stop test-app
docker rm test-app
```
**✅ Success:** Container runs and responds

### Test 8: Run Automated Tests
```bash
./run-tests.sh
```
**✅ Success:** All tests pass

---

## 3️⃣ QUICK VERIFICATION CHECKLIST

Run these one by one:

```bash
# 1. Build works?
mvn clean package && echo "✅ BUILD OK" || echo "❌ FAILED"

# 2. Tests pass?
mvn test && echo "✅ TESTS OK" || echo "❌ FAILED"

# 3. WAR exists?
[ -f target/nexus-web-app-1.0.0-SNAPSHOT.war ] && echo "✅ WAR OK" || echo "❌ MISSING"

# 4. Docker works?
docker ps > /dev/null 2>&1 && echo "✅ DOCKER OK" || echo "❌ NEED LOGOUT/LOGIN"

# 5. All docs exist?
[ -f README.md ] && [ -f AUDIT.md ] && echo "✅ DOCS OK" || echo "❌ MISSING"
```

---

## 4️⃣ BROWSER TESTING (Optional)

### Start Application
```bash
mvn spring-boot:run
```

### Open in Browser
- Home: http://localhost:8080/
- Version: http://localhost:8080/version
- Health: http://localhost:8080/health

**✅ Success:** Pages load in browser

---

## 5️⃣ TROUBLESHOOTING

### Problem: "Port 8080 already in use"
```bash
# Quick fix - use helper script
./kill-port-8080.sh

# OR manually:
lsof -i :8080
kill -9 <PID>
```

### Problem: "Docker permission denied"
```bash
# Add yourself to docker group
sudo usermod -aG docker $USER

# Then LOGOUT and LOGIN again
# Or reboot:
sudo reboot
```

### Problem: "mvn command not found"
```bash
# Install Maven
sudo apt update
sudo apt install maven
```

### Problem: "Java version wrong"
```bash
# Check Java version
java -version

# Should be Java 11 or higher
```

---

## 6️⃣ EXPECTED RESULTS

### Maven Build
```
[INFO] BUILD SUCCESS
[INFO] Total time: XX s
```

### Tests
```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### Application Running
```
Started NexusWebApplication in X.XXX seconds
Tomcat started on port(s): 8080 (http)
```

### Curl Responses
```
$ curl http://localhost:8080/
Welcome to Nexus Artifact Management Demo Application!

$ curl http://localhost:8080/version
Version: 1.0.0-SNAPSHOT

$ curl http://localhost:8080/health
Application is running
```

---

## ✅ YOU'RE DONE!

If all tests pass, your project is **100% ready for submission**!

**What you've verified:**
- ✅ Maven builds successfully
- ✅ Tests pass
- ✅ Application runs
- ✅ Endpoints work
- ✅ Docker builds (optional)
- ✅ All files present

**Project Status: READY** 🎉
