<div class="form-group">
    <label class="col-md-3  control-label">Title  <span class="required">*</span></label>
    <div class="col-md-6">
        <input   required id="title" name="topic" class="form-control" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label">Upload File </label>

    <div class="col-md-6">
        <input type="file"  required id="file" name="file"  class="form-control"/>
        <p>
            Convert all Powerpoint files into pdf. Files must be less than 2 MB. pdf, tiff, gif, bmp, jpeg, jpg, png files  are supported except Microsoft files
        </p>

    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label">Body</label>
    <div class="col-md-10">
        <input type="hidden" id="text" name="text"/>
        <div class="">
            <div  id="descriptionB" class="summernote" data-plugin-summernote  data-plugin-options='{ "height": 180, "codemirror": { "theme": "ambiance"} }'></div>
        </div>
    </div>
</div>

<div class="panel-footer clearfix">
    <a class="btn btn-primary pull-right" href="#save" onclick="dataSubmit()" >Save</a>
</div>
