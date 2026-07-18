Question:
You have been tasked with developing a RESTful APl using Spring Boot for a book management system. The system should allow users to perform CRUD operations
on books stored in a database. You are required to implement the following functionalities:
1. Create a new book by providing its title, author, and publication year.
2. Retrieve a specific book by its unique ID.
3. Update the details of an existing book (title, author, or publication year).
4. Delete a book by its unique ID.
5. Retrieve a list of all books in the database.

Your task is to design and implement the necessary Spring Boot components to fulfill these requirements. Consider using Spring Data JPA for database interaction.
Write the necessary code to implement the RESTful API endpoints and the corresponding service and repository classes. You may assume that the database
configuration and connection are already set up.

Note: You can use any Java IDE or text editor to write the code, However, please provide the complete code for all necessary components to demonstrate your
solution.

Hint: Make sure to define the appropriate entity class for the book and annotate it properly for database mapping.

---

## Prerequisites

- [Podman](https://podman.io/) installed and running
- Java 17+ (for local development without Docker)

## Quick Start with Podman

### 1. Start Podman Machine (First Time Only)

On your first run, you need to start a Podman machine (similar to Docker Desktop):

```bash
podman machine init
podman machine start
```

This may take a moment on the first boot. Verify it's running:

```bash
podman info
```

### 2. Build and Run All Services

From the project root directory:

```bash
podman compose up --build
```

This starts all services defined in `compose.yaml`:

| Service | Port(s) | Description |
|---------|---------|-------------|
| book-service | 8080 | Main REST API |
| notification-service | 8081 | Kafka consumer service |
| db (PostgreSQL) | 5433 | Database |
| redis | 6379 | Cache |
| kafka | 9092 | Message broker |
| kafka-ui | 8090 | Kafka dashboard |

### 3. Verify the Application

Test the API:

```bash
# Health check
curl http://localhost:8080/api/books/hello

# List all routes
curl http://localhost:8080/api/books/routes

# Create a book
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title": "Test Book", "authorName": "Author"}'

# Get all books
curl http://localhost:8080/api/books
```

Swagger UI: `http://localhost:8080/swagger-ui/index.html`
Kafka UI: `http://localhost:8090`

### 4. Stop and Down

Stop all services (keeps data):

```bash
podman compose down
```

Stop and remove all containers, networks (keeps volumes):

```bash
podman compose down
```

Stop and remove everything including volumes (**wipes all data**):

```bash
podman compose down -v
```

## API Endpoints

All endpoints are under base URL: `http://localhost:8080/api/books`

| Method | Endpoint | Example | Description |
|--------|----------|---------|-------------|
| POST | `/api/books` | `http://localhost:8080/api/books` | Create a new book |
| GET | `/api/books/{id}` | `http://localhost:8080/api/books/1` | Get book by ID |
| GET | `/api/books` | `http://localhost:8080/api/books` | Get all books |
| PUT | `/api/books/{id}` | `http://localhost:8080/api/books/1` | Update book by ID |
| DELETE | `/api/books/{id}` | `http://localhost:8080/api/books/1` | Delete book by ID |
| GET | `/api/books/search?book={title}` | `http://localhost:8080/api/books/search?book=Gatsby` | Search books |
| GET | `/api/books/routes` | `http://localhost:8080/api/books/routes` | List all routes |

### Request Body (BookDTO)

```json
{
  "title": "Book Title",
  "authorName": "Author Name",
  "createdBy": "username"
}
```

- `title`: Required, 2-20 characters

# Extras: Local AI Setup Guide

This guide walks you through setting up **Ollama** with a **Qwen** model and connecting it to your IDE for AI-assisted development.

---

## 1. Install Ollama

Download and install Ollama from the official website:
https://ollama.com/

After installation, verify it's running:

```bash
ollama --version
```

Ollama runs by default on `http://localhost:11434`.

Verify the API is responsive:

```bash
curl http://localhost:11434/api/version
```

---

## 2. Pull a Qwen Model

Pull the Qwen model you want to use. Replace `<tag>` with the model size/version:

```bash
# Example: Pull Qwen 2.5 7B (balanced speed/quality)
ollama pull qwen2.5:7b

# Available Qwen models (choose one):
ollama pull qwen2.5:0.5b    # Very fast, lightweight
ollama pull qwen2.5:1.5b    # Fast
ollama pull qwen2.5:3b      # Medium
ollama pull qwen2.5:7b      # Recommended for most users
ollama pull qwen2.5:14b     # Higher quality, needs more RAM
ollama pull qwen2.5:32b     # Highest quality, needs 32GB+ RAM
```

Test the model locally:

```bash
ollama run qwen2.5:7b "Hello, who are you?"
```

---

## 3. Configure Ollama API (Optional)

By default, Ollama listens on `http://localhost:11434`. If you need to change this:

### Windows:
Edit or create `%USERPROFILE%\.ollama\config.json`:

```json
{
  "host": "0.0.0.0",
  "port": 11434
}
```

Then restart Ollama:

```bash
# If running as a service
sc stop ollama
sc start ollama

# Or kill and restart manually
taskkill /F /IM ollama.exe
ollama serve
```

---

## 4. Install AI Extension in VS Code

### Option A: Cline (Recommended for Ollama)

1. Open VS Code
2. Go to Extensions (`Ctrl+Shift+X`)
3. Search for **"Cline"** and install it
4. After installation, open the Cline panel
5. In settings, set:
   - **Provider**: `Ollama`
   - **Model URL**: `http://localhost:11434`
   - **Model Name**: `qwen2.5:7b` (or whichever model you pulled)

### Option B: Continue

1. Install the **"Continue"** extension in VS Code
2. Open Continue settings (`Ctrl+Shift+P` → "Continue: Open Settings")
3. Configure:

```json
{
  "llm": {
    "provider": "ollama",
    "model": "qwen2.5:7b",
    "apiBase": "http://localhost:11434/v1"
  }
}
```

---

## 5. Install AI Extension in IntelliJ IDEA

### Option A: JetBrains AI Assistant (with Ollama)

1. Open IntelliJ IDEA
2. Go to **File → Settings → Tools → AI Assistant**
3. Click **"Add new provider"**
4. Select **"Ollama"**
5. Configure:
   - **API URL**: `http://localhost:11434`
   - **Model**: `qwen2.5:7b`

### Option B: Codeium / Copilot-like extensions

1. Go to **File → Settings → Plugins**
2. Search for **"JanusAI"** or **"Blackbird"** (free Ollama-based plugins)
3. Install and configure with:
   - **API URL**: `http://localhost:11434/v1`
   - **Model**: `qwen2.5:7b`

---

## 6. Verify Everything Works

Test the model via curl (replace with your model name):

```bash
curl http://localhost:11434/api/generate -d '{
  "model": "qwen2.5:7b",
  "prompt": "What is Spring Boot?",
  "stream": false
}'
```

Expected output contains a JSON response with the model's answer.

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| `ollama: command not found` | Reinstall Ollama or add to PATH |
| Connection refused on port 11434 | Make sure `ollama serve` is running (or Ollama app is open) |
| Model too slow / out of memory | Pull a smaller model (`ollama pull qwen2.5:3b`) |
| Cline/Continue not connecting | Verify `curl http://localhost:11434/api/tags` returns your model list |

---

## Useful Commands Summary

```bash
# Start Ollama (if not auto-started)
ollama serve

# List available models
ollama list

# Pull a new model
ollama pull qwen2.5:7b

# Run model interactively
ollama run qwen2.5:7b

# Delete a model
ollama rm qwen2.5:7b