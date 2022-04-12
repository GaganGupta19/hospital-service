Rails.application.routes.draw do
  # Define your application routes per the DSL in https://guides.rubyonrails.org/routing.html

  # Defines the root path route ("/")
  # root "articles#index"

  namespace :api do
    namespace :v1 do
      resources :hospitals, only: [:create, :index]
      resources :vendors, only: [:index, :update]
      resources :patients, only: [:index] do
        collection do
          post :provide_vaccine
        end
      end
    end
  end
end
