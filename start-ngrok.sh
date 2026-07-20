#!/bin/bash
# Check if config has placeholder authtoken
if grep -q "YOUR_NGROK_AUTHTOKEN_HERE" ngrok.yml; then
  echo "⚠️ Warning: Please replace 'YOUR_NGROK_AUTHTOKEN_HERE' with your actual ngrok authtoken inside ngrok.yml"
fi

echo "Starting ngrok tunnel for payment-app..."
ngrok start payment-app --config ngrok.yml
