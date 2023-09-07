FROM node:16-alpine

WORKDIR /pokedex-frontend

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 3000

CMD npm run dev
