FROM ruby:3.0.3
RUN apt-get update -qq && apt-get install -y build-essential libpq-dev nodejs
RUN mkdir /app
WORKDIR /app
ADD Gemfile /app/Gemfile
ADD Gemfile.lock /app/Gemfile.lock
RUN bundle install
ADD . /app

ADD wait.sh /wait.sh
ADD start.sh /start.sh

RUN chmod 755 /start.sh
RUN chmod 755 /wait.sh
