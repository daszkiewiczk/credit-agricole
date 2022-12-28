# specify the node base image with your desired version node:<version>
FROM node:19-bullseye-slim
# replace this with your application's default port

# Create app directory
WORKDIR /usr/src/app

# Install app dependencies
COPY package.json .
RUN npm install

# Bundle app source
COPY . .

# Build the app
RUN npm run build

# Set the production environment
#ENV NODE_ENV=production

# Expose the app's port
EXPOSE 3000

# Start the app
CMD ["npm", "start"]