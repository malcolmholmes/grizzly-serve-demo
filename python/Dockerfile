FROM python:3.12

WORKDIR /app
ADD requirements.txt .
ADD main.py .
RUN python -m pip install -r requirements.txt
CMD ["python", "main.py"]
