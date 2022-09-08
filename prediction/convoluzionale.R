library(keras)
library(tensorflow)


train_dir <- 'train'
test_dir <- 'test'
validation_dir <- 'validation'

training <- function(modelname) {

  train_datagen <- image_data_generator(rescale = 1/255)
  validation_datagen <- image_data_generator(rescale = 1/255)

  train_generator <- flow_images_from_directory(
    train_dir,
    train_datagen,
    target_size = c(150, 150),
    batch_size = 60,
    class_mode = "binary")

  validation_generator <- flow_images_from_directory(
    validation_dir,
    validation_datagen,
    target_size = c(150, 150),
    batch_size = 40,
    class_mode = "binary")

  model <- keras_model_sequential() %>%
    layer_conv_2d(filters = 32, kernel_size = c(3, 3), activation = "relu",
                input_shape = c(150, 150, 3)) %>%
    layer_max_pooling_2d(pool_size = c(2, 2)) %>%
    layer_conv_2d(filters = 64, kernel_size = c(3, 3), activation = "relu") %>%
    layer_max_pooling_2d(pool_size = c(2, 2)) %>%
    layer_conv_2d(filters = 128, kernel_size = c(3, 3), activation = "relu") %>%
    layer_max_pooling_2d(pool_size = c(2, 2)) %>%
    layer_conv_2d(filters = 128, kernel_size = c(3, 3), activation = "relu") %>%
    layer_max_pooling_2d(pool_size = c(2, 2)) %>%
    layer_flatten() %>%
    layer_dense(units = 512, activation = "relu") %>%
    layer_dense(units = 1, activation = "sigmoid")

  model %>% compile(
    loss = "binary_crossentropy",
    optimizer = optimizer_rmsprop(lr = 1e-4),
    metrics = c("accuracy"))

  history <- model %>% fit_generator(
    train_generator,
    steps_per_epoch = 80,
    epochs = 150,
    validation_data = validation_generator,
    validation_steps = 50)

  plot(history)

  test_datagen <- image_data_generator(rescale = 1/255)

  test_generator <- flow_images_from_directory(
    test_dir,
    test_datagen,
    target_size = c(150, 150),
    batch_size = 20,
    class_mode = "binary")

  model %>% evaluate_generator(test_generator, steps = 50)

  # salviamo il modello

  model %>% save_model_hdf5(paste(modelname,".h5",sep=""))
  cat("model saved\n")
}

#0 melanoma
#1 nevi
prediction <- function(modelname, imagename) {
	 


  fmodel <- paste(modelname,".h5",sep="")
  if(file.exists(fmodel) && file.exists(imagename)) {
    modelp <- load_model_hdf5(fmodel)
    
    img <- image_load(imagename, target_size = c(150, 150))
    img_array <- image_to_array(img)
    img_array <- array_reshape(img_array, c(1, 150, 150, 3))
    
    res <- (modelp %>% predict(img_array))
    cat(res) #"(0 = melanoma, 1 = nevi)"
  } else {
    cat("-1")
  }



}

args <- commandArgs(trailingOnly = TRUE)

prediction("modello_cnn_melanoma_2410",args)



