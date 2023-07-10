FROM node:14-alpine

ARG PROJ_DIR=/vue_proj

RUN mkdir -p ${PROJ_DIR}
COPY ./ ${PROJ_DIR}

WORKDIR ${PROJ_DIR}

RUN npm install -g npm

COPY ./package.json ${PROJ_DIR}
COPY ./package-lock.json ${PROJ_DIR}

RUN npm install

CMD ["npm","run","serve"]