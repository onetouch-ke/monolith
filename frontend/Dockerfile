# frontend/Dockerfile

# 1단계: Build
FROM node:18 as builder
WORKDIR /app
COPY . .
RUN npm install && npm run build

# 2단계: Serve with nginx
FROM nginx:alpine
COPY --from=builder /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf

# nginx 실행 명령 추가
CMD ["nginx", "-g", "daemon off;"]
