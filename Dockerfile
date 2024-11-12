FROM node:18-alpine
WORKDIR /home/node/app
COPY package*.json ./
RUN npm start
COPY . .
EXPOSE 9999
CMD ["node", "app.js"]